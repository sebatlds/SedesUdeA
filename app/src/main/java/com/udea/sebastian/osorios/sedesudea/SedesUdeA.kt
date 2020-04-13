package com.udea.sebastian.osorios.sedesudea

import java.lang.NullPointerException

class SedesUdeA {

    fun getNameLocation() : Array<String>{
        return arrayOf("UdeA Ciudad Universitaria","UdeA Sede Robledo", "UdeA Sede de Posgrados",
        "UdeA Paraninfo", "UdeA Seccional Oriente", "Udea Seccional Occidente", "UdeA Sede Bajo Cauca",
        "UdeA Sede Sonson", "UdeA Facultad Enferemeria", "UdeA Facultad Medicina")
    }

    fun getPosition(name : String): Array<Double>{
        var position : Array<Double>
        when (name){
            "UdeA Ciudad Universitaria"->{
                position =  arrayOf(6.269542, -75.568187)
            }
            "UdeA Sede Robledo"->{
                position = arrayOf(6.272649, -75.587668)
            }
            "UdeA Sede de Posgrados"->{
                position = arrayOf(6.198320, -75.584211)
            }
            "UdeA Paraninfo"->{
                position = arrayOf(6.246732, -75.564581)
            }
            "UdeA Seccional Oriente"->{
                position =  arrayOf(6.105791, -75.387569)
            }
            "Udea Seccional Occidente"->{
                position =  arrayOf(6.555268,-75.826482)
            }
            "UdeA Sede Bajo Cauca"->{
                position = arrayOf(7.991905, -75.201452)
            }
            "UdeA Sede Sonson"->{
                position = arrayOf(5.719070, -75.296694)
            }
            "UdeA Facultad Enferemeria"->{
                position = arrayOf(6.262041,-75.568131)
            }
            "UdeA Facultad Medicina"->{
                position = arrayOf(6.261377, -75.566333)
            }else ->{
                position = arrayOf(0.0)
            }

        }
        return position
    }

}