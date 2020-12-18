package com.example.madlevel5taks1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSave.setOnClickListener{
            saveNote()
        }

        observeNote()

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    private fun observeNote() {
        viewModel.note.observe(viewLifecycleOwner, Observer {
            note -> note?.let {
                tilNoteTitle.editText?.setText(it.title)
                tilNoteText.editText?.setText(it.text)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            message -> Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, Observer {
            success -> findNavController().popBackStack()
        })
    }

    private fun saveNote() {
        viewModel.updateNote(tilNoteTitle.editText?.text.toString(),
        tilNoteText.editText?.text.toString())
    }


}