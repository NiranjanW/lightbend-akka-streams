package com.lightbend.akkassembly

import akka.NotUsed
import akka.stream.scaladsl.{Flow, Source}

class PaintShop (colorSet:Set[Color]){
  val colors:Source[Color,NotUsed] = Source.cycle(() =>colorSet.iterator)

  def paint():Flow[UnfinishedCar, UnfinishedCar, NotUsed] = {
    Flow[UnfinishedCar].zip(colors).map(i => i._1.paint((i._2)))
  }
}
