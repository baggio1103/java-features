## Introduction to Method Handle

An example demonstrating what Method Handle 
is and how it can be used.

A method handle is a typed, directly executable
reference to an underlying method, constructor, 
field, or similar low-level operation, with optional
transformations of arguments or return values. 
These transformations are quite general and include 
such patterns as conversion, insertion, deletion, 
and substitution.

In other words, a method handle is a lightweight 
reference to a method, constructor, or even a field 
that can be executed to invoke the method or to 
do an operation on a field to read or write its value.
Method handles are immutable and have no visible 
state but the underlying methods bound to by the
handle do exhibit a state.

This example will show how a MethodHandle is a
much more modern alternative to the traditional 
old school Reflection API and how it overcomes 
its performance issues.
