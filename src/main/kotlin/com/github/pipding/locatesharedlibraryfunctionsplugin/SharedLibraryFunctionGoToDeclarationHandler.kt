//package com.github.pipding.locatesharedlibraryfunctionsplugin
//
//import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler
//import com.intellij.openapi.editor.Editor
//import com.intellij.psi.PsiElement
//import com.intellij.psi.util.parentOfType
//
//class SharedLibraryFunctionGoToDeclarationHandler : GotoDeclarationHandler {
//    override fun getGotoDeclarationTargets(sourceElement: PsiElement?, offset: Int, editor: Editor?): Array<PsiElement>? {
//        if (sourceElement == null) {
//            return null
//        }
//
//        val toml = findTomlFile(sourceElement, sourceElement.text)
//        if (toml != null) {
//            return arrayOf(toml)
//        }
//
//        return null
//    }
//
//    private fun findTomlFile(context: PsiElement, name: String): TomlFile? {
//        context.getRootGradleProjectPath()
//        val module = context.containingFile?.originalFile?.virtualFile?.let {
//            ProjectFileIndex.getInstance(context.project).getModuleForFile(it)
//        } ?: return null
//        val tomlPath = context.project.service<VersionCatalogsLocator>().getVersionCatalogsForModule(module)[name] ?: return null
//        val toml = VfsUtil.findFile(tomlPath, false) ?: return null
//        return PsiManager.getInstance(context.project).findFile(toml)?.castSafelyTo<TomlFile>()
//    }
//}