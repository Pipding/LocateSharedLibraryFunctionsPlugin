package com.github.pipding.locatesharedlibraryfunctionsplugin.services

import com.intellij.openapi.project.Project
import com.github.pipding.locatesharedlibraryfunctionsplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
