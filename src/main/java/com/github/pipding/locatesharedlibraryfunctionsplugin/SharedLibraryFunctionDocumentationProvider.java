package com.github.pipding.locatesharedlibraryfunctionsplugin;

import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SharedLibraryFunctionDocumentationProvider implements DocumentationProvider {

    @Override
    public @Nullable PsiElement getCustomDocumentationElement(@NotNull Editor editor, @NotNull PsiFile file, @Nullable PsiElement contextElement, int targetOffset) {

        // Let the super try first
        PsiElement target = DocumentationProvider.super.getCustomDocumentationElement(editor, file, contextElement, targetOffset);

        if (target == null) {
           // Is the target a file?
           target = SharedLibraryFunctionUtility.FindSharedLibraryScriptByElement(contextElement);

           // If it's still null it's not a file, so check if it's a function
           if (target == null) {
               target = SharedLibraryFunctionUtility.FindSharedLibraryFunctionByElement(contextElement);
           }
        }

        return target;
    }
}
