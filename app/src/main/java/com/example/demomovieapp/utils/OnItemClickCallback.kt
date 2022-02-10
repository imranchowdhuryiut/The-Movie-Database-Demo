package com.example.demomovieapp.utils

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */
interface OnItemClickCallback<Model> {
    fun onClick(model: Model)
}