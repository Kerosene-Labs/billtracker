export { matchers } from './matchers.js';

export const nodes = [
	() => import('./nodes/0'),
	() => import('./nodes/1'),
	() => import('./nodes/2'),
	() => import('./nodes/3'),
	() => import('./nodes/4'),
	() => import('./nodes/5'),
	() => import('./nodes/6'),
	() => import('./nodes/7'),
	() => import('./nodes/8'),
	() => import('./nodes/9'),
	() => import('./nodes/10'),
	() => import('./nodes/11'),
	() => import('./nodes/12'),
	() => import('./nodes/13'),
	() => import('./nodes/14'),
	() => import('./nodes/15')
];

export const server_loads = [];

export const dictionary = {
		"/": [7],
		"/app/expenses": [8,[2]],
		"/app/expenses/createOneOff": [9,[2]],
		"/app/expenses/recurring/create": [11,[2]],
		"/app/expenses/recurring/[id]": [10,[2]],
		"/app/home": [12,[2,3]],
		"/app/settings": [13,[2,4]],
		"/login": [14,[5]],
		"/oauth/google": [15,[6]]
	};

export const hooks = {
	handleError: (({ error }) => { console.error(error) }),
	
	reroute: (() => {}),
	transport: {}
};

export const decoders = Object.fromEntries(Object.entries(hooks.transport).map(([k, v]) => [k, v.decode]));

export const decode = (type, value) => decoders[type](value);

export { default as root } from '../root.js';