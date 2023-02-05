603 – Software Development Engineer in Test (Backend)
Imagine you are building a social network. Starting from simple functionality. Users are now
able to make posts and comment on them. You are working in the backend team that
exposes the service: https://jsonplaceholder.typicode.com/ which has the following
endpoints:

1. Make posts: https://jsonplaceholder.typicode.com/posts
2. Comment on posts: https://jsonplaceholder.typicode.com/comments
3. List of users: https://jsonplaceholder.typicode.com/users

Using Rest-Assured, Cucumber, and Java, create a few scenarios to test this functionality.
Please submit a complete working project either in a .zip, or a link to the project on GitHub.

You will be assessed on the following:
• Quality of BDD scenarios
• Quality of tests and coverage
• Readability and understanding of the code
• Structure of project
• Design patterns and abstraction
• Dependency management

Resources
JSONPlaceholder comes with a set of 6 common resources:
```
/posts 100 posts
/comments 500 comments
/albums 100 albums
/photos 5000 photos
/todos 200 todos
/users 10 users
```
Note: resources have relations. For example: posts have many comments, albums have many photos, ... see guide for the
full list.

Routes
All HTTP methods are supported. You can use http or https for your requests.
```
GET /posts
GET /posts/1
GET /posts/1/comments
GET /comments?postId=1
POST /posts
PUT /posts/1
PATCH /posts/1
DELETE /posts/1
```
Note: see guide for usage examples.

Getting a resource
```
fetch('https://jsonplaceholder.typicode.com/posts/1')
.then((response) => response.json())
.then((json) => console.log(json));
```
👇 Output
```
{
id: 1,
title: '...',
body: '...',
userId: 1
}
```
Listing all resources

```
fetch('https://jsonplaceholder.typicode.com/posts')
.then((response) => response.json())
.then((json) => console.log(json));
```
👇 Output
```
[
{ id: 1, title: '...' /* ... */ },
{ id: 2, title: '...' /* ... */ },
{ id: 3, title: '...' /* ... */ },
/* ... */
{ id: 100, title: '...' /* ... */ },
];
```
Creating a resource
```
fetch('https://jsonplaceholder.typicode.com/posts', {
method: 'POST',
body: JSON.stringify({
title: 'foo',
body: 'bar',
userId: 1,
}),
headers: {
'Content-type': 'application/json; charset=UTF-8',
},
})
.then((response) => response.json())
.then((json) => console.log(json));
```
👇 Output
```
{
id: 101,
title: 'foo',
body: 'bar',
userId: 1
}
```
Important: resource will not be really updated on the server but it will be faked as if.

Updating a resource
```
fetch('https://jsonplaceholder.typicode.com/posts/1', {
method: 'PUT',
body: JSON.stringify({
id: 1,
title: 'foo',
body: 'bar',
userId: 1,
}),
headers: {
'Content-type': 'application/json; charset=UTF-8',
},
})
.then((response) => response.json())
.then((json) => console.log(json));
```
👇 Output
```
{
id: 1,
title: 'foo',
body: 'bar',
userId: 1
}
```
Important: resource will not be really updated on the server but it will be faked as if.

Patching a resource
```
fetch('https://jsonplaceholder.typicode.com/posts/1', {
method: 'PATCH',
body: JSON.stringify({
title: 'foo',
}),
headers: {
'Content-type': 'application/json; charset=UTF-8',
},
})
.then((response) => response.json())
.then((json) => console.log(json));
```
👇 Output
```
{
id: 1,
title: 'foo',
body: '...',
userId: 1
}
```
Important: resource will not be really updated on the server but it will be faked as if.

Deleting a resource
```
fetch('https://jsonplaceholder.typicode.com/posts/1', {
method: 'DELETE',
});
```
Important: resource will not be really updated on the server but it will be faked as if.

Filtering resources
Basic filtering is supported through query parameters.
```
// This will return all the posts that belong to the first user
fetch('https://jsonplaceholder.typicode.com/posts?userId=1')
.then((response) => response.json())
.then((json) => console.log(json));
```
Listing nested resources
One level of nested route is available.
```
// This is equivalent to /comments?postId=1
fetch('https://jsonplaceholder.typicode.com/posts/1/comments')
.then((response) => response.json())
.then((json) => console.log(json));
```
The available nested routes are:
```
/posts/1/comments
/albums/1/photos
/users/1/albums
/users/1/todos
/users/1/posts
```

