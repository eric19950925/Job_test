package com.philabnb.job_test

import com.philabnb.job_test.Base.Navigator
import org.koin.dsl.module

val coordinator = module {
    single { Navigator() }
}