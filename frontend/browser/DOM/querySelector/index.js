import querySelector from "./querySelector";

/**
 * Replace the string arg to match on desired DOM Node
 */
try {
  console.log(querySelector("facet"));
} catch (err) {
  console.log(err);
}
