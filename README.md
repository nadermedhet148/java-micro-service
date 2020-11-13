# Microservices SAGA pattern example in Java with spring boot

## Introduction
This is a basic example of the micro-services SAGA pattern.  
It's just aimed at introducing the concept with some concrete materials

## Overview
This example shows order with payment proccess.  
The principle consists in having separate microservices to create,and payments but also having all those elements handled in an atomic transaction.

## Saga pattern
A micro-services world is polyglot.  
It involves services written in different languages and writing into different storage technology.  
Not all of them will even understand the concept of a transaction, so a 2PC kind of transaction is not possible within a micro-services architecture.  
   
The "atomicity" therefore relies on the SAGA pattern.  
The management of the Users, Accounts and Cards information is organized in a sequence of events rather than being synchronized in parallel actions.  
The error recovery mechanism is rather based on compensations than rollback mechanisms.  

The SAGA pattern implies:  

 * that we are able to identify "retryable" vs "non-retryable" errors.  Retryable errors will be tried again; non retryable errors will be compensated  

 * that the services are idempotents  
this is because a service can be retried (retryable errors) while the concept of a rollback is not at use so data could have already been persisted before the retry  

 * that the entire system is thought with "eventual consistency" in mind  
once again, because there is no rollbacks but compensations, data can be persisted and then deleted, leading to a window where the information is inconsistent

## Components
The system consists in:
- Order-Service
- Payment-Service
- Kafka-broker
 

# Demo

## Runinng

