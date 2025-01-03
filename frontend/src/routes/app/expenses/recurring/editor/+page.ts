export function load({ url }) {
  const id = url.searchParams.get('id');
  return { id };
}
