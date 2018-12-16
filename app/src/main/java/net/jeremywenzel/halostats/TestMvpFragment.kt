package net.jeremywenzel.halostats

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import okhttp3.Request

class TestMvpFragment: BaseMvpFragment<TestView, TestPresenter>(), TestView {
    override fun createPresenter(): TestPresenter {
        return TestPresenterImpl()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.test_view, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        TestAsync().execute()
    }

    private class TestAsync: AsyncTask<Void, Void, Void?>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            RequestProcessor.makeRequest(Request.Builder().url("https://www.google.com").build())
            return null
        }

    }
}