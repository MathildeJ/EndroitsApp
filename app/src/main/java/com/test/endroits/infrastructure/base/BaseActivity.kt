package com.test.endroits.infrastructure.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.test.endroits.EndroitsApplication
import com.test.endroits.infrastructure.di.components.AppComponent
import com.test.endroits.infrastructure.di.components.DaggerFragmentComponent
import com.test.endroits.infrastructure.di.components.FragmentComponent
import com.test.endroits.infrastructure.di.components.HasComponent
import com.test.endroits.infrastructure.di.modules.ActivityModule

abstract class BaseActivity: AppCompatActivity(), HasComponent<FragmentComponent> {

    private lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
        injectDependencies()
        setContentView(getLayoutResource())
    }

    protected abstract fun getLayoutResource(): Int

    private fun injectDependencies() {
        getApplicationComponent().inject(this)
    }

    protected fun getApplicationComponent(): AppComponent {
        return (application as EndroitsApplication).getAppComponent()
    }

    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }

    private fun initializeInjector() {
        fragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
    }
    override val component: FragmentComponent
        get() = fragmentComponent

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}