package com.github.pipding.locatesharedlibraryfunctionsplugin;

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: Pick up from here. You wanted to add some nice syntax highlighting to references to files in the vars folder.
//  Stuff like buildNumberHelper, which is currently greyed out.
//  To accomplish this you were looking at this page https://plugins.jetbrains.com/docs/intellij/syntax-highlighting-and-error-highlighting.html
//  Which pointed you in the direction of creating a lexer https://plugins.jetbrains.com/docs/intellij/implementing-lexer.html
public class SharedLibraryFunctionSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
    @Override
    public @NotNull SyntaxHighlighter getSyntaxHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile) {
        return null;
    }
}
