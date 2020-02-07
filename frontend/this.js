/*
  This binding depends on 4 conditions  
  - are we using an arrow function and therefore this lexically bound?
  - is this hard-bound using .bind()?
  - what is the context of the call? i.e. call-site
  - is this implicitly lost?
 */
function calculateYearsLeft() {
  return this.expectedGraduationYear - this.currentYear;
}

var student = {
  calculateYearsLeft,
  expectedGraduationYear: 2016,
  currentYear: 2014
};

var yearsLeft = student.calculateYearsLeft;
// var currentYear = 2020
yearsLeft();

function foo(something) {
  this.a = something;
}

var obj1 = {
  foo: foo
};

var obj2 = {};

obj1.foo(2);
console.log(obj1.a);

obj1.foo.call(obj2, 3);
console.log(obj2.a);

var bar = new obj1.foo(4);
console.log(obj1.a);
console.log(bar.a);

// 2 - implicitly this bound
// 3 - call uses an array of args and pass them to the foo fcn explicitly binding
// 4 - new takes precedence over an implicit bind, therefore the new value is 4
// 4 - we lookup the prototype chain and find an a property reference

function foo() {
  console.log(this.a);
}

var obj1 = {
  a: 2,
  foo: foo
};

var obj2 = {
  a: 3,
  foo: foo
};

obj1.foo();
obj2.foo();

obj1.foo.call(obj2);
obj2.foo.call(obj1);

// 2
// 3
/* 
this = {
  a: 3,
  foo: foo
}
*/
// 3
// 2

function foo(something) {
  console.log('something', something);
  this.a = something;
}

var obj1 = {};

var bar = foo.bind(obj1);
bar(2);
console.log(obj1.a);

var baz = new bar(3);
console.log(obj1.a);
console.log(baz.a);
/*
  this = {}
*/
// we explicit hard bind, so the value of something is either undefined
// or its 2
// 2 because
//
//

function foo() {
  console.log(this.a);
}

var a = 2;
var o = { a: 3, foo: foo };
var p = { a: 4 };

o.foo();
(p.foo = o.foo)();
