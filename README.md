# KeepDishesComming - Backend
Anna Mestres 

## Challenges & Accomplishments

Challenges faced

One of the most intricate challenges was designing a consistent domain model and flow across multiple bounded contexts Orders, Baskets, and Restaurants while ensuring each remained independent yet interoperable. Establishing clean mappings between domain models, JPA entities, and external projections required careful design to avoid redundancy and maintain data integrity.

Implementing basket consistency rules proved especially demanding. Enforcing the constraint that a basket may only contain dishes from a single restaurant and must automatically block checkout when a dish becomes unpublished or out of stock required domain-driven coordination between aggregate roots, event propagation, and validation at both the application and persistence layers.

Another significant challenge was handling state synchronization between the restaurant and order contexts. When restaurants update dish availability or accept/reject orders, these changes must cascade through the system in real time, without breaking transaction boundaries or causing stale data. Designing for eventual consistency through domain events and asynchronous communication required deep attention to transactional safety and business logic sequencing.

Building out the persistence layer with complex composite keys (e.g., DishProjectionId, BasketItemId) and avoiding Hibernate mapping conflicts was also nontrivial. Careful use of @Embeddable, @MapsId, and schema alignment was essential to prevent duplication issues and maintain referential integrity.

Proud achievements

Despite the challenges, the resulting system demonstrates a strong DDD architecture with clearly defined boundaries and smooth collaboration between contexts. The basket-to-order workflow now provides a consistent and reliable experience: customers can build baskets, review their contents, and safely proceed to checkout with immutable order snapshots, even when menu items change afterward.

Implementing real-time validation and blocking checkout logic ensures that the customer’s basket always reflects the restaurant’s current offerings, improving both user trust and operational integrity. The order tracking feature, powered by event-driven updates, provides clear visibility into the order lifecycle—from placement to restaurant acceptance, preparation, and readiness.

From a technical standpoint, the modular structure of the project, combined with robust mapping layers and explicit domain models, has made the system easier to test, evolve, and extend. The use of domain events, aggregate patterns, and value objects ensures a high level of maintainability and domain fidelity.

Overall, this project evolved into a production-ready architecture that balances business complexity with clean design principles, delivering both reliability and scalability for future growth.

---

## ✅ Finished Features

List all features that were successfully implemented and tested.

- [x] Completed ordering flow
- [x] Enabled editing of a dish as a draft without affecting the live menu
- [x] Added publish/unpublish functionality for menu items
- [x] sign up/sign in to access my restaurant management area.
- [x] create my restaurant by submitting name, full address, contact email, picture(s) url, default preparation time, type of cuisine, and opening hours.
- [x] schedule a set of publishes/unpublishes to go live together at a chosen time.
- [x] mark a dish out of stock or back in stock immediately.
- [x] set opening hours and manually open/close the restaurant at any moment.
- [x] accept or reject new orders and provide a reason on rejection.
- [x] mark an accepted order ready for pickup.
- [x] landing page to let me continue as a customer.
- [x] explore restaurants in a list or on a map.
- [x] view a restaurant’s details and dishes.
- [x] filter restaurants by type of cuisine, price range
- [x] filter dishes by type (e.g., starter or dessert) and by food tags (lactose, gluten,..)
- [x] sensible sorting options (e.g., price) when viewing dishes.
- [x] build a basket from a single restaurant’s published dishes.
- [x] checkout to be blocked if any dish in my basket becomes out of stock or unpublished while I’m browsing.
- [x] to provide my name, delivery address, and contact email at checkout.
- [x] to pay using the payment provider during checkout.
- [x] a confirmation with a link so I can return to track my order.
- [x] track the progress of my order as its status changes.
- [x] no more than 10 dishes to be available to customers at any moment.
- [x] publish messages for the delivery service when an order is accepted and when it is ready for pickup.
- [x] consume delivery service messages for picked up, delivered, and courier locations to update orders.
- [x] customers to be able to order without signing up or signing in.
- [x] Orders without a decision within five minutes to be automatically declined.


---

## ❌ Unfinished / Planned Features

List features that are planned, in progress, or not yet implemented.

Example:

- [ ] Apply all pending dish changes in one action.
- [ ] filter restaurants by distance, and guesstimated delivery time.
- [ ] see a guesstimated delivery time based on location, default preparation time, and busyness.
- [ ] see the price range evolution of a restaurant
- [ ] to adjust the price ranges criteria
- [ ] each owner to manage exactly one restaurant.
