
## Get post by id
Method: `GET`
```
/api/post/{post-id}
```
Example:  
```
/api/post/123
```

## Get all posts
Method: `GET`
```
/api/posts
```

## Get all posts filtered by a tag
Method: `GET`
```
/api/posts?tag={tag-name}
```
Example:  
```
/api/posts?tag=programming
```

## Create a post
Method: `POST`
```
/api/post
```

Body:
```
{
    "topic": "<topic>",
    "content": "<content>",
    "tags": [
        "<tag>", "<tag>"
    ],
    "createdAt": "<created-date>",
    "profileId": <profile-id>
}
```
Example:  
```
/api/post
```

```{
    "topic": "Operating Systems",
    "content": "Contributed significantly to the development of UNIX and modern OS principles.",
    "tags": [
        "<systems>", "<unix>"
    ],
    "createdAt": "2030-12-12",
    "profileId": 456
}
```

## Update a post
Method: `PUT`
```
/api/post/{post-id}
```

Body:
```
{
    "topic": "<topic>",
    "content": "<content>",
    "tags": [
        "<tag>", "<tag>"
    ],
    "createdAt": "<created-date>",
    "profileId": <profile-id>
}
```
Example:  
```
/api/post/123
```

```{
    "topic": "All Operating Systems",
    "content": "Contributed significantly to the development of UNIX and modern OS principles.",
    "tags": [
        "<linux>", "<unix>"
    ],
    "modifieddAt": "2050-01-01",
    "profileId": 456
}
```
