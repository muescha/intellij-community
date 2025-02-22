// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.webSymbols.utils;

import com.intellij.markdown.utils.doc.DocMarkdownToHtmlConverter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @deprecated Use {@link DocMarkdownToHtmlConverter} directly
 */
@Deprecated(forRemoval = true)
public final class HtmlMarkdownUtils {

  private HtmlMarkdownUtils() {
  }

  @Contract(pure = true)
  public static @NotNull String toHtml(@NotNull String markdownText) {
    return DocMarkdownToHtmlConverter.convert(markdownText);
  }


  @Contract(pure = true)
  public static @NotNull String toHtml(@NotNull String markdownText, boolean convertTagCodeBlocks) {
    return DocMarkdownToHtmlConverter.convert(markdownText);
  }

}
