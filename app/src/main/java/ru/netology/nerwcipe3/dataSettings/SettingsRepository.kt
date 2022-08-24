package ru.netology.nerwcipe3.dataSettings

interface SettingsRepository {

    fun saveStateSwitch(key: String, b: Boolean)
    fun getStateSwitch(key: String):Boolean

}