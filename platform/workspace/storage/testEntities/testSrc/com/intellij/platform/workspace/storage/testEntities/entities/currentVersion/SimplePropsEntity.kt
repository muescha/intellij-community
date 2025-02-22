// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.platform.workspace.storage.testEntities.entities.currentVersion

import com.intellij.platform.workspace.storage.*
import com.intellij.platform.workspace.storage.impl.containers.toMutableWorkspaceList
import com.intellij.platform.workspace.storage.impl.containers.toMutableWorkspaceSet

interface SimplePropsEntity: WorkspaceEntity {
  val text: String
  val list: List<Int>
  val set: Set<List<String>>
  val map: Map<Set<String>, List<String>> // Change is here Set<Int> to Set<String>
  val bool: Boolean

  //region generated code
  @GeneratedCodeApiVersion(2)
  interface Builder : SimplePropsEntity, WorkspaceEntity.Builder<SimplePropsEntity> {
    override var entitySource: EntitySource
    override var text: String
    override var list: MutableList<Int>
    override var set: MutableSet<List<String>>
    override var map: Map<Set<String>, List<String>>
    override var bool: Boolean
  }

  companion object : EntityType<SimplePropsEntity, Builder>() {
    @JvmOverloads
    @JvmStatic
    @JvmName("create")
    operator fun invoke(text: String,
                        list: List<Int>,
                        set: Set<List<String>>,
                        map: Map<Set<String>, List<String>>,
                        bool: Boolean,
                        entitySource: EntitySource,
                        init: (Builder.() -> Unit)? = null): SimplePropsEntity {
      val builder = builder()
      builder.text = text
      builder.list = list.toMutableWorkspaceList()
      builder.set = set.toMutableWorkspaceSet()
      builder.map = map
      builder.bool = bool
      builder.entitySource = entitySource
      init?.invoke(builder)
      return builder
    }
  }
  //endregion
}

//region generated code
fun MutableEntityStorage.modifyEntity(entity: SimplePropsEntity,
                                      modification: SimplePropsEntity.Builder.() -> Unit): SimplePropsEntity = modifyEntity(
  SimplePropsEntity.Builder::class.java, entity, modification)
//endregion
