package com.github.pipding.locatesharedlibraryfunctionsplugin;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SharedLibraryFunctionGoToDeclarationHandler implements GotoDeclarationHandler {
    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement sourceElement, int offset, Editor editor) {

        PsiElement target;

        // Is the selected element a filename?
        target = SharedLibraryFunctionUtility.FindSharedLibraryScriptByElement(sourceElement);

        if (target != null) {
            return List.of(target).toArray(new PsiElement[0]);
        }

        target = SharedLibraryFunctionUtility.FindSharedLibraryFunctionByElement(sourceElement);

        if (target == null) {
            return new PsiElement[0];
        }
        else {
            return List.of(target).toArray(new PsiElement[0]);
        }
    }

    @Override
    public @Nullable @Nls(capitalization = Nls.Capitalization.Title) String getActionText(@NotNull DataContext context) {
        return GotoDeclarationHandler.super.getActionText(context);
    }
}
