## **Requirement Document: Multi-Group Chat Application**

### **1. Introduction**
The purpose of this document is to outline the requirements for building a multi-group chat application. This application will allow users to engage in real-time messaging within multiple groups. The backend will use **Java Spring**, **Kafka** for message brokering, and **MongoDB** for persistence. The frontend will be developed using **Angular**.

---

### **2. Functional Requirements**

#### **2.1 User Management**
1. **Authentication**:
   - Users must authenticate via email and password.
   - Spring Security and JWT will be used to secure endpoints and WebSocket connections.
2. **User Sessions**:
   - Users can maintain active sessions and reconnect automatically if disconnected.

#### **2.2 Group Management**
1. **Group Creation**:
   - A user can create a group by specifying a unique group name.
   - The creator becomes the **host** of the group.
2. **Group Participation**:
   - Users can join a group by subscribing to the group topic.
   - Users cannot join groups that do not exist.

#### **2.3 Messaging**
1. **Real-Time Messaging**:
   - Users can send and receive messages in real time within a group.
   - Messages are broadcasted to all users subscribed to the group.
2. **Message Persistence**:
   - All messages are stored in **MongoDB** with the following fields:
     - `groupName`: Name of the group.
     - `sender`: The user who sent the message.
     - `content`: The content of the message.
     - `timestamp`: The time when the message was sent.
3. **Message History**:
   - Users can fetch the chat history of a group.
   - Messages will be fetched in chronological order.

---

### **3. Non-Functional Requirements**

#### **3.1 Performance**
- The system must support up to **1,000 concurrent users** across multiple groups.
- Kafka will handle high-throughput message brokering to ensure scalability.

#### **3.2 Scalability**
- The application should handle dynamic scaling for increasing user loads.
- Groups and topics should be lightweight to allow seamless creation.

#### **3.3 Security**
- All API endpoints and WebSocket connections must be secured using JWT.
- Users can only send/receive messages within groups they are subscribed to.

#### **3.4 Reliability**
- Kafka must guarantee message delivery to ensure no messages are lost.
- MongoDB must persist all chat messages for retrieval during outages or reconnections.

#### **3.5 Maintainability**
- The codebase will follow clean architecture principles to ensure easy updates and scalability.
- The project structure will be feature-based to avoid future refactoring.

---

### **4. System Design**

#### **4.1 Architecture Overview**
The system will follow a microservices-like architecture:
1. **Frontend**:
   - Developed using Angular.
   - Connects via WebSocket for real-time messaging.
   - Uses REST APIs to fetch historical messages and manage groups.
2. **Backend**:
   - Java Spring for WebSocket and REST APIs.
   - Kafka for message brokering.
   - MongoDB for persistence.

#### **4.2 Data Flow**
1. User sends a message via WebSocket.
2. Backend publishes the message to Kafka.
3. Kafka consumer:
   - Saves the message to MongoDB.
   - Broadcasts the message to all users in the group via WebSocket.

---

### **5. APIs and WebSocket Endpoints**

#### **5.1 REST APIs**

| Endpoint                        | Method | Description                          |
|---------------------------------|--------|--------------------------------------|
| `/api/auth/signup`              | POST   | Register a new user.                |
| `/api/auth/login`               | POST   | Authenticate a user.                |
| `/api/groups`                   | POST   | Create a new group.                 |
| `/api/groups/{groupName}`       | GET    | Fetch group details.                |
| `/api/chat/history/{groupName}` | GET    | Fetch message history for a group.  |

#### **5.2 WebSocket Endpoints**

| Endpoint                       | Description                                |
|--------------------------------|--------------------------------------------|
| `/ws`                          | WebSocket connection endpoint.            |
| `/app/send/{groupName}`        | Send a message to a group.                |
| `/topic/group/{groupName}`     | Subscribe to receive group messages.      |

---

### **6. Tools and Technologies**

#### **Backend**
- Java Spring Boot
- Kafka (Message Broker)
- MongoDB (Persistence)
- Spring Security with JWT (Authentication)

#### **Frontend**
- Angular for the user interface
- RxJS for handling reactive data streams
- SockJS and Stomp.js for WebSocket communication

#### **DevOps**
- Docker for containerization

---

### **7. Milestones**

#### **Phase 1: Backend Setup**
- Configure MongoDB, Kafka, and Java Spring Boot project.
- Create WebSocket configuration and define `/ws` endpoint.
- Set up JWT-based authentication for WebSocket connections and REST APIs.
- Implement REST APIs for user authentication (`/auth/signup` and `/auth/login`).

#### **Phase 2: Messaging Backend**
- Implement WebSocket endpoints:
  - `/app/send/{groupName}` to handle incoming messages.
  - `/topic/group/{groupName}` for broadcasting messages to a group.
- Configure Kafka producer and consumer:
  - **Producer**: Publish messages from WebSocket to Kafka.
  - **Consumer**: Save messages to MongoDB and broadcast them to WebSocket subscribers.
- Implement message persistence in MongoDB:
  - Define `ChatMessage` document schema.
  - Create repository/service for saving and retrieving messages.

#### **Phase 3: Frontend Integration**
- Set up Angular project and integrate SockJS and Stomp.js for WebSocket communication.
- Implement the following features:
  - Real-time message sending and receiving.
  - Chatroom UI for displaying messages.
  - Subscription to group topics (`/topic/group/{groupName}`).
- Add user authentication using JWT:
  - Implement login and signup forms.

#### **Phase 4: Group Features**
- Add APIs for creating and managing groups.
- Implement group joining logic on both frontend and backend:
  - Users can only subscribe to topics they have joined.
- Add UI components for group management:
  - Group creation and selection.

#### **Phase 5: Message History**
- Implement REST API to fetch chat history for a group.
- Add a feature on the frontend to load historical messages on entering a group.

#### **Phase 6: Testing and Optimization**
- Perform unit and integration tests for:
  - WebSocket messaging.
  - Kafka producer-consumer flow.
  - MongoDB persistence.
  - Angular WebSocket integration.
- Perform load testing with 1,000 concurrent users.
- Optimize MongoDB queries and Kafka configurations for better performance.

#### **Phase 7: Deployment**
- Dockerize the application for easy deployment.
- Deploy to a cloud platform (AWS, GCP, or Azure).
