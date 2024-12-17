package tsd.poc.annotation

import scala.annotation.internal.Repeated
import scala.annotation.{Annotation, StaticAnnotation}
import scala.annotation.meta.field

@field
@Repeated
final case class AttributeMapping(targetAttribute: String, targetClass: Class[_]) extends StaticAnnotation
