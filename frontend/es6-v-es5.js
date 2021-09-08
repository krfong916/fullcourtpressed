// a static method is added to the function object, and not the prototype of the object

// ES6
class Teacher {
  static instructions() {}
  getLessonPlan() {}
}

// ES5
function Teacher() {}
Teacher.instructions = function() {};
Teacher.prototype.getLessonPlan = function() {};

Teacher.instructions(); // call method on function object
var science = new Teacher();
science.instructions(); // Uncaught TypeError: science.instructions is not a function

function User(email, password, token) {
  this.email = email;
  this.password = password;
  this.token = token;
}

User.create = function(email, password, token) {
  return new User(email, password, token);
};

class User {
  constructor(email, password, token) {
    this.email = email;
    this.password = password;
    this.token = token;
  }
  static create(email, password, token) {
    return new User(email, password, token);
  }
}
