package com.lightbend.akkassembly

import akka.NotUsed
import akka.stream.ActorAttributes.SupervisionStrategy
import akka.stream.{ActorAttributes, Supervision}
import akka.stream.scaladsl.Flow
import com.lightbend.akkassembly.QualityAssurance.CarFailedException

object QualityAssurance {
  case class CarFailedException(car:UnfinishedCar)extends IllegalStateException(s"Car Failed inspection: $car")
}

class QualityAssurance  {

//  val inspect:Flow[UnfinishedCar ,Car,NotUsed] =
//  Flow[UnfinishedCar].collect{
//    case x if(x.engine != None && !x.wheels.isEmpty && x.color !=None) =>
//      Car(SerialNumber() ,x.color.get ,x.engine.get, x.wheels,None)
//  }


  val inspect:Flow[UnfinishedCar ,Car,NotUsed] = {

    val decider:Supervision.Decider = {
      case CarFailedException(car) =>Supervision.Resume

  }
    Flow[UnfinishedCar].map{
      case UnfinishedCar(Some(color: Color) ,Some(engine: Engine), wheels,upgrade: Upgrade) if wheels.size ==4
        => Car(SerialNumber() ,color,engine,wheels,upgrade)
      case car => throw CarFailedException(car)
    }.withAttributes(ActorAttributes.supervisionStrategy(decider))
  }

}
