package data

import domain.Setting
import domain.SettingsDataSource
import domain.toSetting
import domain.toSettingsEntity
import satrapin.satrap.Database

class SqlDelightSettings(db: Database) : SettingsDataSource {
    private val queries = db.settingsQueries

    override suspend fun insertSetting(setting: Setting) {
        queries.insertSetting(setting.toSettingsEntity())
    }

    override suspend fun getSetting(setting: String): Setting? {
        return queries.getSetting(setting).executeAsOneOrNull()?.toSetting()
    }

    override suspend fun getAllSettings(): List<Setting> {
        return queries.getAllSettings().executeAsList().map { it.toSetting() }
    }
}