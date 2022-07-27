package com.ifsp.edu.br.pdm.businesscard

import android.app.Application
import com.ifsp.edu.br.pdm.businesscard.data.AppDatabase
import com.ifsp.edu.br.pdm.businesscard.data.BusinessCardRepository

class App: Application() {
    val database by lazy{ AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}