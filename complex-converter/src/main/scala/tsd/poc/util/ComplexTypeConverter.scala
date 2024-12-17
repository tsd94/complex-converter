package tsd.poc.util

import tsd.poc.annotation.AttributeMapping
import scala.reflect.ClassTag
import scala.reflect.runtime.universe._
object ComplexTypeConverter:

  /**
   * Converts an object of one type to another type.
   *
   * @param source the source object to convert
   * @param targetClass the class of the target object
   * @tparam T the type of the target object
   * @return the converted object of type T
   * @throws RuntimeException if the conversion fails
   */
  def convert[T: ClassTag](source: AnyRef, targetClass: Class[T]): T =
    try
      // Create a new instance of the target class
      val target = targetClass.getDeclaredConstructor().newInstance()

      // Get all fields of the source and target classes
      val sourceFields = source.getClass.getDeclaredFields
      val targetFields = targetClass.getDeclaredFields

      // Iterate over each field in the source class
      for sourceField <- sourceFields do
        sourceField.setAccessible(true)
        var targetFieldName = sourceField.getName

        // Check if the source field is annotated with AttributeMapping
        if sourceField.isAnnotationPresent(classOf[AttributeMapping]) then
          val mappings = sourceField.getAnnotationsByType(classOf[AttributeMapping])
          for mapping <- mappings do
            // If the target class matches, use the specified target attribute name
            if mapping.targetClass() == targetClass then
              targetFieldName = mapping.targetAttribute()

        // Iterate over each field in the target class
        for targetField <- targetFields do
          // If the field names and types match, set the value from the source to the target
          if targetField.getName == targetFieldName && sourceField.getType == targetField.getType then
            targetField.setAccessible(true)
            targetField.set(target, sourceField.get(source))
            targetField.setAccessible(false)

        sourceField.setAccessible(false)

      target
    catch
      case e: Exception => throw new RuntimeException("Conversion failed", e)