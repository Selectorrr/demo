package com.example.demo;

import com.google.common.collect.ImmutableSet;
import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.servlet.ApolloScalars;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.UnaryOperator;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    GraphQLSchema schema() {
        return GraphQLSchema.newSchema()
                .query(GraphQLObjectType.newObject()
                        .name("query")
                        .field(field -> field
                                .name("test")
                                .type(Scalars.GraphQLString)
                                .dataFetcher(environment -> "test")
                        )
                        .build())
                .mutation(GraphQLObjectType.newObject()
                        .name("mutation")
                        .field(field -> field
                                .name("singleUpload")
                                .type(Scalars.GraphQLString)
                                .argument(builder -> builder.name("file").type(ApolloScalars.Upload))
                                .dataFetcher(environment -> "response")
                        )
                        .build())
                .additionalTypes(ImmutableSet.of(ApolloScalars.Upload))
                .build();
    }
}
