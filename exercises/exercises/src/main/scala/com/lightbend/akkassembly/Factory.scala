package com.lightbend.akkassembly

import akka.stream.Materializer

import scala.concurrent.Future
import akka.stream.scaladsl.Sink

class Factory(bodyShop:BodyShop , paintShop:PaintShop,engineShop:EngineShop,wheelShop:WheelShop,qa:QualityAssurance
             ,upgradeShop: UpgradeShop) (implicit materializer: Materializer)
{
  def orderCars(qty:Int, upgradeShop: UpgradeShop):Future[Seq[Car]] = {
   val cars = bodyShop.cars
   val engines =engineShop.engines

    val completedOrder = cars.zip(engines).map(c =>c._1.installEngine(c._2))
      .via(paintShop.paint)
      .via(wheelShop.installWheels)
      .via(qa.inspect)
      .take(qty)
      .runWith(Sink.seq)

    completedOrder
  }

}
