## 0.9.0

- Rewritten to bazel and documents updated

## 0.8.0

### Addressing missing field

- A field that is documented as required is missing on dual faced cards, updating `card_back_id` to be nullable on `Card`s

## 0.7.0

### Code and collector number query

- Adding the ability to get a specific card by set and collector number

## 0.6.0

### Call logging

- Adding an optional logging to see the raw queries that are sent. To use set `logcalls` to true when creating your client
- Fixing issue where a name query next into another query did not conform to scryfall requirements

## 0.5.1

### AndQuery

- Internal fixes to how and queries are constructed