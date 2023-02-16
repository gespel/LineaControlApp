package com.example.lineacontrol

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lineacontrol.databinding.FragmentFirstBinding
import lineacontrol.core.Channel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var ana: AndroidNetworkingAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ana = AndroidNetworkingAdapter(activity)
        binding.bMute1.setOnClickListener {
            ana.setMute(Channel.Out1)
        }
        binding.bMute2.setOnClickListener {
            ana.setMute(Channel.Out2)
        }
        binding.bMute3.setOnClickListener {
            ana.setMute(Channel.Out3)
        }
        binding.bMute4.setOnClickListener {
            ana.setMute(Channel.Out4)
        }
        binding.bMute5.setOnClickListener {
            ana.setMute(Channel.Out5)
        }
        binding.bMute6.setOnClickListener {
            ana.setMute(Channel.Out6)
        }
        binding.bMute7.setOnClickListener {
            ana.setMute(Channel.Out7)
        }
        binding.bMute8.setOnClickListener {
            ana.setMute(Channel.Out8)
        }
        binding.bConnect.setOnClickListener {
            ana.connect(binding.connectInput.text.toString())
        }
        binding.buttonFirst.setOnClickListener {
            val networkHandlerThread = HandlerThread("UP")
            networkHandlerThread.start()
            var networkHandler = Handler(networkHandlerThread.looper)
            networkHandler.post(object : Runnable {
                override fun run() {
                    try {
                        ana.update()
                        var x = ana.inputMeters
                        //var y = ana.inputGains
                        binding.inputoutput1.text = x[0].toString()
                        binding.inputoutput2.text = x[1].toString()
                        binding.inputoutput3.text = x[2].toString()
                        binding.inputoutput4.text = x[3].toString()

                        /*binding.inputGain1.setText(y[0].toString())
                        binding.inputGain2.setText(y[1].toString())
                        binding.inputGain3.setText(y[2].toString())
                        binding.inputGain4.setText(y[3].toString())*/
                    }
                    catch (e: Exception) {
                        //Toast.makeText(null, "Bla", 3)
                    }
                    networkHandler.postDelayed(this, 100)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}