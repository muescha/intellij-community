package com.intellij.codeInspection.tests.kotlin.logging

import com.intellij.jvm.analysis.internal.testFramework.logging.LoggingSimilarMessageInspectionTestBase
import com.intellij.jvm.analysis.testFramework.JvmLanguage

class KotlinLoggingSimilarMessageInspectionTest : LoggingSimilarMessageInspectionTestBase() {

  fun `test equals log4j2`() {
    myFixture.testHighlighting(JvmLanguage.KOTLIN, """
      import org.apache.logging.log4j.LogManager
      import org.apache.logging.log4j.Logger
      
      internal class Logging {
          private val logger: Logger = LogManager.getLogger()
      
          private fun request1(i: String) {
              val msg = "log messages: {}"
              logger.<weak_warning descr="Similar log messages">info(msg, i)</weak_warning>
          }
      
          private fun request2(i: Int) {
              val msg = "log messages: {}"
              logger.<weak_warning descr="Similar log messages">info(msg, i)</weak_warning>
          }
      }
    """.trimIndent())
  }

  fun `test equals slf4j`() {
    myFixture.testHighlighting(JvmLanguage.KOTLIN, """
      import org.slf4j.Logger
      import org.slf4j.LoggerFactory
      
      internal class Logging {
          private val LOG: Logger = LoggerFactory.getLogger(Logging::class.<error descr="[UNRESOLVED_REFERENCE] Unresolved reference: java">java</error>)
      
          private fun request1(i: String) {
              val msg = "log messages: {}"
              LOG.<weak_warning descr="Similar log messages">info(msg, i)</weak_warning>
              LOG.info("1" + msg, i)
          }
      
          private fun request2(i: Int) {
              val msg = "log messages: {}"
              LOG.<weak_warning descr="Similar log messages">info(msg, i)</weak_warning>
          }
      }
    """.trimIndent())
  }
}

