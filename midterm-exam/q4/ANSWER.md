# Q4 — Your answer

## 1. Predicted output

TODO(Q045): write the exact output, line by line.

```text
machine 30 min / a machine
washer 50 min / a machine
dryer 50 min / a machine
a dryer / a machine / dryer
dryer 40 min
```

## 2. Static overloading vs dynamic dispatch

TODO(Q045): why `kind(m)` uses the compile-time type but `m.name()` / `m.minutes()` use the run-time type.

```text
Overloaded methods are resolved by the compiler, at compile time, using only
the declared (static) types of the arguments. In kind(m), m is declared as
Machine, so the compiler binds the call to kind(Machine). It never looks at
which object m refers to at run time, even if that object is a Dryer.

Overridden instance methods such as name() and minutes() are resolved at run
time by virtual dispatch: the JVM looks at the actual class of the object and
runs that class's version of the method. So m.name() returns "dryer" because
the object is a Dryer, even though m is declared as Machine. The same applies
to the calls inside describe(): name() and minutes() are dispatched on the
run-time type of the object.

In short: overloading is chosen by the type of the reference (compile time),
overriding is chosen by the type of the object (run time).

Python has no overloading, so kind() is a single function that checks the
run-time type with isinstance(). It therefore returns "a dryer" for any Dryer,
and the Python output differs from Java on lines 3 and 4 ("dryer 50 min / a
dryer" and "a dryer / a dryer / dryer").
```

## 3. The bad cast

TODO(Q045): what happens at `Dryer wrong = (Dryer) w;` in Java — and what the
equivalent Python line does.

```text
In Java, Dryer wrong = (Dryer) w; compiles, because w is declared as Machine
and Dryer is a subclass of Machine, so the cast is allowed by the compiler.
At run time, the JVM checks the real type of the object: w refers to a Washer,
which is not a Dryer, so a ClassCastException is thrown and the program stops.
Using instanceof before the cast would avoid this.

Python has no casts and no compile-time type check. The line w.set_heat(4) is
accepted as written, and the problem is found only when it runs: the Washer
object has no set_heat attribute, so Python raises an AttributeError.

Both languages fail at run time, but Java fails at the cast itself, while
Python fails when the missing method is called.
```