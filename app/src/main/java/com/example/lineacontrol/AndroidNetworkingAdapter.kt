package com.example.lineacontrol
import android.app.AlertDialog
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lineacontrol.core.*
import java.lang.Exception

class AndroidNetworkingAdapter(var activity: FragmentActivity?) {
    var inputMeters = mutableListOf(0.0f, 0.0f, 0.0f, 0.0f)
    var inputGains = mutableListOf(0.0f, 0.0f, 0.0f, 0.0f)
    var mute1: Boolean = false
    var mute2: Boolean = false
    var mute3: Boolean = false
    var mute4: Boolean = false
    var mute5: Boolean = false
    var mute6: Boolean = false
    var mute7: Boolean = false
    var mute8: Boolean = false
    var updateCounter = 0
    lateinit var l: LineaControl
    init {

    }
    public fun connect(ip: String) {
        val job = GlobalScope.launch {
            try {
                Looper.prepare();
                l = LineaControl(ip, true)
                l.unmuteAllOutputs()
                l.unmuteAllInputs()
                /*val builder = AlertDialog.Builder(activity)
                builder.setTitle("Erfolg!")
                builder.setMessage("Verbindung zum Gerät wurde hergestellt!")

                builder.setPositiveButton("OK") { dialog, which ->
                    // Hier können Sie den Code hinzufügen, der ausgeführt wird, wenn der Benutzer auf OK klickt
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()*/
            }
            catch (e: Exception) {/*
                val builder = AlertDialog.Builder(activity)
                builder.setTitle("FEHLER!")
                builder.setMessage("Es konnte keine Verbindung zum Gerät wurde hergestellt!")

                builder.setPositiveButton("OK") { dialog, which ->
                    // Hier können Sie den Code hinzufügen, der ausgeführt wird, wenn der Benutzer auf OK klickt
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()*/
            }

        }
    }
    fun getInputMeters() {
        val job = GlobalScope.launch {
            inputMeters[0] = l.getInputMeter(Channel.InA)
            inputMeters[1] = l.getInputMeter(Channel.InB)
            inputMeters[2] = l.getInputMeter(Channel.InC)
            inputMeters[3] = l.getInputMeter(Channel.InD)
        }
    }
    fun getInputGains() {
        val job = GlobalScope.launch {
            inputGains[0] = l.getInputGain(Channel.InA)
            inputGains[1] = l.getInputGain(Channel.InB)
            inputGains[2] = l.getInputGain(Channel.InC)
            inputGains[3] = l.getInputGain(Channel.InD)
        }
    }
    public fun setMute(channel: Channel) {

        if(channel == Channel.Out1) {
            mute1 = mute1 != true
            val job = GlobalScope.launch {
                l.setMute(channel, mute1)
            }
        }
        if(channel == Channel.Out2) {
            mute2 = mute2 != true
            val job = GlobalScope.launch {
                l.setMute(channel, mute2)
            }
        }
        if(channel == Channel.Out3) {
            mute3 = mute3 != true
            val job = GlobalScope.launch {
                l.setMute(channel, mute3)
            }
        }
        if(channel == Channel.Out4) {
            mute4 = mute4 != true
            val job = GlobalScope.launch {
                l.setMute(channel, mute4)
            }
        }
        if(channel == Channel.Out5) {
            mute5 = mute5 != true
            val job = GlobalScope.launch {
                l.setMute(channel, mute5)
            }
        }
        if(channel == Channel.Out6) {
            mute6 = mute6 != true
            val job = GlobalScope.launch {
                l.setMute(channel, mute6)
            }
        }
        if(channel == Channel.Out7) {
            mute7 = mute7 != true
            val job = GlobalScope.launch {
                l.setMute(channel, mute7)
            }
        }
        if(channel == Channel.Out8) {
            mute8 = mute8 != true
            val job = GlobalScope.launch {
                l.setMute(channel, mute8)
            }
        }


    }
    public fun update() {
        updateCounter++
        getInputMeters()
    }
}