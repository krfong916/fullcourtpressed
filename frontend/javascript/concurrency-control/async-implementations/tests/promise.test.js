import { Promised } from "../promise";

describe("ES6 Promises From Scratch", () => {
  test("Promise has resolve, reject, and thenable methods defined on object", () => {
    const p = new Promised();
    // expect(p.hasOwnProperty("resolve")).toBeTruthy();
    // expect(p.hasOwnProperty("reject")).toBeTruthy();
    // expect(p.hasOwnProperty("then")).toBeTruthy();
  });

  test("Usage", () => {
    // console.log(new Promise((resolve) => {}));
    // const a = new Promised((resolve, reject) => {
    //   return resolve(5);
    // });
    const firstThen = new Promised((resolve, reject) => {
      resolve(42);
    });
    console.log(firstThen);
    // console.log(new Promise((resolve, reject) => {}));
    // Promise { <pending> }
    // console.log(a);

    // const b = new Promised((resolve, reject) => {
    //   setTimeout(() => resolve(1), 4000);
    // });
  });

  test("Returns a pending state", () => {});

  test("Resolution handlers are invoked: either onFulfilled() or onRejected() when promise resolves", () => {});

  test("A promise resolves with the expected value", () => {});

  test("We can chain promises together", () => {});

  test("The reason for the exception is returned", () => {});

  test("An error does not execute succeeding calls", () => {});

  test(".all[] API executes promises concurrently, and waits for all responses to return before completing execution", () => {});
});
