package tsd.poc.annotation

import scala.annotation.{StaticAnnotation, Annotation}
import scala.annotation.meta.field

@field
final case class AttributeMappingContainer(value: Array[AttributeMapping]) extends Annotation
