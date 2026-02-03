package io.micronaut.inject.builder;

import io.micronaut.core.annotation.AnnotationMetadata;

import java.util.List;

public interface BeanDefinitionBuilder<T, M, F> {

    void constructor(ConstructorDefinition<T> constructorDefinition);

    void addMethodInjection(MethodDefinition<T, M> methodDefinition);

    void addFieldInjection(FieldDefinition<T, F> fieldDefinition);

    void addPostConstruct(MethodDefinition<T, M> methodDefinition);

    void addPreDestroy(MethodDefinition<T, M> methodDefinition);

    record ConstructorDefinition<K>(K owningType,
                                    AnnotationMetadata annotationMetadata,
                                    List<BeanDefinitionInjectionPoint<K>> injectionPoints,
                                    boolean requiresReflection) implements AnnotationMetadataProviderRecordStyle {
    }

    record MethodDefinition<K, M>(M methodElement,
                                  AnnotationMetadata annotationMetadata,
                                  List<BeanDefinitionInjectionPoint<K>> injectionPoints,
                                  boolean requiresReflection) implements AnnotationMetadataProviderRecordStyle {
    }

    record FieldDefinition<K, F>(F fieldElement,
                                 AnnotationMetadata annotationMetadata,
                                 BeanDefinitionInjectionPoint<K> injectionPoint,
                                 boolean requiresReflection,
                                 boolean isOptional) implements AnnotationMetadataProviderRecordStyle {
    }
}
