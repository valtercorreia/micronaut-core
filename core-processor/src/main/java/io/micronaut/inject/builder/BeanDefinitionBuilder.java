package io.micronaut.inject.builder;

import io.micronaut.core.annotation.AnnotationMetadata;

import java.util.List;

public interface BeanDefinitionBuilder<T, C, M, F> {

    void constructor(ConstructorDefinition<T, C> constructorDefinition);

    void factoryMethod(MethodDefinition<T, M> methodDefinition);

    void factoryField(FieldDefinition<T, F> fieldDefinition);

    void addMethodInjection(MethodDefinition<T, M> methodDefinition);

    void addFieldInjection(FieldDefinition<T, F> fieldDefinition);

    void addPostConstruct(MethodDefinition<T, M> methodDefinition);

    void addPreDestroy(MethodDefinition<T, M> methodDefinition);

    record ConstructorDefinition<K, C>(C constructorElement,
                                       AnnotationMetadata annotationMetadata,
                                       List<BeanDefinitionInjectionPoint<K>> injectionPoints,
                                       boolean requiresReflection) implements MemberDefinition<K> {
    }

    record MethodDefinition<K, M>(M methodElement,
                                  AnnotationMetadata annotationMetadata,
                                  List<BeanDefinitionInjectionPoint<K>> injectionPoints,
                                  boolean requiresReflection) implements MemberDefinition<K> {
    }

    record FieldDefinition<K, F>(F fieldElement,
                                 AnnotationMetadata annotationMetadata,
                                 BeanDefinitionInjectionPoint<K> injectionPoint,
                                 boolean requiresReflection,
                                 boolean isOptional) implements MemberDefinition<K> {
    }

    sealed interface MemberDefinition<K> extends AnnotationMetadataProviderRecordStyle {
    }
}
