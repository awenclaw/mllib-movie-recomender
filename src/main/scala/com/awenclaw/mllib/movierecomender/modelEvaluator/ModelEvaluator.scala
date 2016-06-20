package com.awenclaw.mllib.movierecomender.modelEvaluator

import org.apache.spark.mllib.recommendation.{Rating, MatrixFactorizationModel}
import org.apache.spark.rdd.RDD


object ModelEvaluator {
  implicit class AlsModelEvaluator(val model: MatrixFactorizationModel){

    def calculateRMSE(testDataSet:RDD[Rating], trainDataSet:RDD[Rating]):Double = {
      val realData = testDataSet.map({
        case Rating(user, item, rating) => ((user, item), rating)
      })

      val userProduct = testDataSet.map({
        case Rating(user, item, rating) => (user, item)
      })
      val predictedData = model.predict(userProduct).map({
        case Rating(user, item, rating) => ((user, item), rating)
      })

      val mse = realData.join(predictedData)
        .map({case ((user, item), (r1, r2)) =>
          Math.pow((r1- r2), 2.0)
        }).mean()

      Math.sqrt(mse)
    }

  }

}
