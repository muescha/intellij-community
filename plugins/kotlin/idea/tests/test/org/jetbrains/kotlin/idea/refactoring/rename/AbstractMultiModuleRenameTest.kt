// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.kotlin.idea.refactoring.rename

import com.intellij.psi.PsiManager
import junit.framework.TestCase
import org.jetbrains.kotlin.idea.jsonUtils.getString
import org.jetbrains.kotlin.idea.test.IDEA_TEST_DATA_DIR
import org.jetbrains.kotlin.idea.test.KotlinMultiFileTestCase
import java.io.File

abstract class AbstractMultiModuleRenameTest : KotlinMultiFileTestCase() {
    override fun getTestRoot(): String = "/refactoring/renameMultiModule/"
    override fun getTestDataDirectory() = IDEA_TEST_DATA_DIR

    open fun isFirPlugin(): Boolean = false

    fun doTest(path: String) {
        val renameParamsObject = loadTestConfiguration(File(path))

        val file = renameParamsObject.getString("file")
        val newName = renameParamsObject.getString("newName")
        val testIsEnabledInK2 = renameParamsObject.get(if (isFirPlugin()) "enabledInK2" else "enabledInK1")?.asBoolean != false

        isMultiModule = true

        val results = runCatching {
            doTestCommittingDocuments { rootDir, _ ->
                val mainFile = rootDir.findFileByRelativePath(file)!!
                val psiFile = PsiManager.getInstance(project).findFile(mainFile)!!

                val renameType = renameParamsObject.getString("type")

                when (RenameType.valueOf(renameType)) {
                    RenameType.FILE -> runRenameProcessor(project, newName, psiFile, renameParamsObject, true, true)
                    RenameType.MARKED_ELEMENT -> doRenameMarkedElement(renameParamsObject, psiFile)
                    else -> TestCase.fail("Unexpected rename type: $renameType")
                }
            }
        }

        results.fold(
            onSuccess = { require(testIsEnabledInK2) { "This test passes and should be enabled!" } },
            onFailure = { exception -> if (testIsEnabledInK2) throw exception }
        )
    }
}
