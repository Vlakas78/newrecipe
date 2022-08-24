package ru.netology.nerwcipe3.adapter

import ru.netology.nerwcipe3.classes.Stage


interface StageInteractionListener {

    fun onRemoveStageClicked(stage: Stage)
    fun onSaveStageClicked(content: String, uriPhoto: String?)
    fun onMoveStageUpClicked(position: Int)
    fun onMoveStageDownClicked(position: Int)
}