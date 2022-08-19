package com.github.pipding.locatesharedlibraryfunctionsplugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SharedLibraryFunctionUtility {

    /**
     * Searches through all files in the current project for a function declaration. This function is intended for locating
     * 2 types of function declarations, both of which are typically found in the vars folder of a Jenkins shared library
     * The first is a "call()" method inside a groovy file in the vars folder. By convention, those functions are called
     * using the filename of the containing script
     * The second is other named methods inside a groovy file. Those functions are called using the format
     * filename.function_name()
     * @param sourceElement The Psi Element to look for
     * @return Returns the PsiElement of the declaration if one is found, otherwise returns null
     */
    public static PsiElement FindSharedLibraryFunctionByElement(@Nullable PsiElement sourceElement) {
        // TODO: When you check for multiple methods in one file, it feels kind of flimsy. Is there a recursive way
        //  to do that? Is it even worth investigating? Could be that there's no way to have more than 2 dots
        //  (like buildNumberHelper.ToInt.toJobBuildNumber.ToString? Does that even make sense? Not really...

        // Setup vars
        Project project = ProjectManager.getInstance().getOpenProjects()[0];
        List<PsiElement> results = new ArrayList();
        String searchingFor = "call(";

        // What is the actual text for the thing we want to goto?
        String methodName = sourceElement.getText();

        // TODO: It would be good if you could restrict this to only searching specific folders (e.g. vars)
        // See if there's a file by that name
        var allFilesWithName = FilenameIndex.getFilesByName(
                project,
                methodName + ".groovy",
                GlobalSearchScope.everythingScope(project)
        );

        // TODO: Make the function not grey if I know where it is



        // No such file
        if (allFilesWithName.length == 0) {

            // Maybe this call is one of multiple methods in a single file
            // in which case get its parent & take the bit before the period
            var elementParent = sourceElement.getParent().getText().split("\\.")[0];

            allFilesWithName = FilenameIndex.getFilesByName(
                    project,
                    elementParent + ".groovy",
                    GlobalSearchScope.everythingScope(project)
            );

            if (allFilesWithName.length == 0) return null;

            searchingFor = sourceElement.getText() + "(";
        }

        // Get every PSI element in the file
        var targetFile = allFilesWithName[0];
        var allPsiElementsInFile = targetFile.getChildren();
        List<PsiElement> allMethodsInFile = new ArrayList<>();

        for (var psiElement: allPsiElementsInFile) {
            if(psiElement.getClass().getName().contains("Method")) {
                allMethodsInFile.add(psiElement);
            }
        }


        // Check to see if there's a call() function in the file
        for (var psiMethodElement: allMethodsInFile) {
            if (psiMethodElement.getText().contains(searchingFor))
            {
                // Function found, return as a goto target
                results.add(psiMethodElement);
            }
        }

        // TODO: If there's more than one element being returned, try to find a better match based on arg type?
        return results.toArray(new PsiElement[0])[0];
    }

    public static PsiElement FindSharedLibraryScriptByElement(@Nullable PsiElement sourceElement) {

        // Setup vars
        Project project = ProjectManager.getInstance().getOpenProjects()[0];
        String searchingFor = "call(";

        // What is the actual text for the thing we want to goto?
        String methodName = sourceElement.getText();

        // TODO: It would be good if you could restrict this to only searching specific folders (e.g. vars)
        // See if there's a file by that name
        var allFilesWithName = FilenameIndex.getFilesByName(
                project,
                methodName + ".groovy",
                GlobalSearchScope.everythingScope(project)
        );

        // File found?
        if (allFilesWithName.length > 0) {
            return allFilesWithName[0];
        }
        else {
            return null;
        }
    }
}
