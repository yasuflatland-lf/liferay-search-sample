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

## Configuration
1. Deploy the portlet on the same page as the Search Results portlet.
2. Go to the Configuration menu.
3. Add the tag names and their respective weight. The higher the weight, the greater the priority.
4. After saving the configuration, run the search again to see the results.

## Backend
- CustomOrderSharedSearchContributor.java
    - This class transforms the search query to a Function Score Query.
    - The AssetTagNames and weight are read from the `CustomOrderPortletPreferences`
