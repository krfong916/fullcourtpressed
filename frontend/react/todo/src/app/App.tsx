import React from 'react';
export default function App({ initialName = '' }) {
  const [name, setName] = React.useState(initialName);

  function handleChange(event: React.FormEvent<HTMLInputElement>) {
    setName(event.currentTarget.value);
  }

  return (
    <div>
      <form>
        <label htmlFor="name">Name: </label>
        <input onChange={handleChange} />
      </form>
      {name ? <strong>Hello {name}</strong> : 'Please type your name'}
    </div>
  );
}
