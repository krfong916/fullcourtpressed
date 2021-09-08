// [[{}], [{}], [{}], [{}]]
const [privilegeId, occupationId, domainId] = collection
  .map(row =>
    row.reduce((ids, entry) => {
      Object.keys(entry).forEach(key => ids.push(entry[key]));
      return ids;
    }, [])
  )
  .flat();

let ids = [];
for (let row of collection) {
  for (let entry of row) {
    Object.keys(entry).forEach(key => ids.push(entry[key]));
  }
}
const [privilegeId, occupationId, domainId] = ids;
