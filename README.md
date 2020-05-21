# Liferay Search Ranking Order Sample

## Requirement
- Liferay 7.2
- Java 8 SDK

## Senario
A manufacturing company, AggreLife, has a dealer portal and sharing manuals of their products such as tractors for dealers. On Liferay, the manuals are implemented with custom structures and templates.

A product is consists of multiple parts. At AggreLife, there are two types of web content, a manual and blueprint of parts. A manual is associated with parts blueprints. For example, A product A manual is related to blueprints of its components with product ID as below.
```
├── Product\ A
│   ├── Parts\ A
│   ├── Parts\ B
│   └── Parts\ C
└── Product\ B
    ├── Parts\ D
    ├── Parts\ E
    └── Parts\ F
```

Given that, the User Stories are as below:
- As a user, I always want to see product in the top of the search result, even other parts A,B and C hits too.
- As a user, I use a tag to identify a product or a component, with "product" and "component."