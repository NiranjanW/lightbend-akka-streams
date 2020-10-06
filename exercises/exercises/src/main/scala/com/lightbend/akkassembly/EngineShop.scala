package com.lightbend.akkassembly

import akka.NotUsed
import akka.stream.scaladsl.{Flow, Source}

class EngineShop(shipmentSize:Int){

  def shipment = Seq.fill(shipmentSize)(Engine())

  val shipments:Source[Shipment,NotUsed] = Source.fromIterator(
    () =>Iterator.continually({Shipment(shipment)})
  )

  val engines :Source[Engine,NotUsed] = shipments.flatMapConcat(i =>Source(i.engines))

  val installEngine:Flow[UnfinishedCar ,UnfinishedCar,NotUsed] =
    Flow[UnfinishedCar].zip(engines).map( e =>e._1.installEngine(e._2))


}
