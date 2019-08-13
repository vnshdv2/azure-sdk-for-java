# Azure Storage Queue client library for Java
Azure Queue storage is a service for storing large numbers of messages that can be accessed from anywhere in the world via authenticated calls using HTTP or HTTPS. 
A single queue message can be up to 64 KB in size, and a queue can contain millions of messages, up to the total capacity limit of a storage account.

[Source code][source_code] | [API reference documentation][api_documentation] | [Product documentation][storage_docs] |
[Samples][samples]

## Getting started

### Prerequisites

- [Java Development Kit (JDK)][jdk] with version 8 or above
- [Azure Subscription][azure_subscription]
- [Create Storage Account][storage_account]

### Adding the package to your product

```xml
<dependency>
  <groupId>com.azure</groupId>
  <artifactId>azure-storage-queue</artifactId>
  <version>12.0.0-preview.2</version>
</dependency>
```

### Create a Storage Account
To create a Storage Account you can use the Azure Portal or [Azure CLI][azure_cli].

```Powershell
az group create --name storage-resource-group --location westus
```

### Authenticate the client

In order to interact with the Storage service (Blob, Queue, Message, MessageId, File) you'll need to create an instance of the Service Client class. 
To make this possible you'll need the Account SAS (shared access signature) string of Storage account. Learn more at [SAS Token][sas_token]

#### Get Credentials

- **SAS Token**
 
a. Use the [Azure CLI][azure_cli] snippet below to get the SAS token from the Storage account.

```Powershell
az storage queue generate-sas
    --name {queue name}
    --expiry {date/time to expire SAS token}
    --permission {permission to grant}
    --connection-string {connection string of the storage account}
```

```Powershell
CONNECTION_STRING=<connection-string>
az storage queue generate-sas
    --name javasdksas
    --expiry 2019-06-05
    --permission rpau
    --connection-string $CONNECTION_STRING
```
b. Alternatively, get the Account SAS Token from the Azure Portal.
```
Go to your storage account -> Shared access signature -> Click on Generate SAS and connection string (after setup)
```

- **Shared Key Credential**

a. Use account name and account key. Account name is your storage account name.
```
// Here is where we get the key
Go to your storage account -> Access keys -> Key 1/ Key 2 -> Key
```
b. Use the connection string
```
// Here is where we get the key
Go to your storage account -> Access Keys -> Keys 1/ Key 2 -> Connection string
```
## Key concepts
### URL format
Queues are addressable using the following URL format:
https://<storage account>.queue.core.windows.net/<queue>
The following URL addresses a queue in the diagram:
https://myaccount.queue.core.windows.net/images-to-download

#### Resource URI Syntax
For the storage account, the base URI for queue operations includes the name of the account only:
```$xslt
https://myaccount.queue.core.windows.net
```
For a queue, the base URI includes the name of the account and the name of the queue:
```$xslt
https://myaccount.queue.core.windows.net/myqueue
```

### Handling Exceptions

```java
TODO
```

### Queue Names
Every queue within an account must have a unique name. The queue name must be a valid DNS name, and cannot be changed once created. Queue names must confirm to the following rules:
1. A queue name must start with a letter or number, and can only contain letters, numbers, and the dash (-) character.
1. The first and last letters in the queue name must be alphanumeric. The dash (-) character cannot be the first or last character. Consecutive dash characters are not permitted in the queue name.
1. All letters in a queue name must be lowercase.
1. A queue name must be from 3 through 63 characters long.

### Queue Services
The queue service do operations on the queues in the storage account and manage the queue properties. 

### Queue Service Client

The client performs the interactions with the Queue service, create or delete a queue, getting and setting Queue properties, list queues in account, and get queue statistics. An asynchronous, `QueueServiceAsyncClient`, and synchronous, `QueueClient`, client exists in the SDK allowing for selection of a client based on an application's use case.
Once you have the value of the SASToken you can create the queue service client with `${accountName}`, `${SASToken}`.
```Java
String queueServiceURL = String.format("https://%s.queue.core.windows.net", accountName)
QueueServiceClient queueServiceClient = new QueueServiceClientBuilder().endpoint(queueURL).credential(SASToken).build();

QueueClient newQueueServiceClient = queueServiceClient.createQueue("myqueue");
```

or

```Java
String queueServiceAsyncURL = String.format("https://%s.queue.core.windows.net/", accountName)
QueueServiceAsyncClient queueServiceAsyncClient = new QueueServiceClientBuilder().endpoint(queueServiceAsyncURL)
                                                        credential(SASToken).build();
queueServiceAsyncClient.createQueue("newAsyncQueue").subscribe(
    result -> {
      // do something when new queue created
    },
    error -> {
      // do something if something wrong happened
    },
    () -> {
      // completed, do something
    });
```

### Queue
Azure Queue storage is a service for storing large numbers of messages that can be accessed from anywhere in the world via authenticated calls using HTTP or HTTPS. 
A single queue message can be up to 64 KB in size, and a queue can contain millions of messages, up to the total capacity limit of a storage account.

### QueueClient
Once you have the value of the SASToken you can create the queue service client with `${accountName}`, `${queueName}`, `${SASToken}`.
```Java
String queueURL = String.format("https://%s.queue.core.windows.net/%s", accountName, queueName);
QueueClient queueClient = QueueClient.builder().endpoint(queueURL).credential(SASToken).build();
// metadata is map of key-value pair, timeout is client side timeout
QueueClient newQueueClient = queueClient.create(metadata, timeout);
```

or

```Java
String queueAsyncURL = String.format("https://%s.queue.core.windows.net/%s%s", accountName, queueAsyncName, sasToken)
QueueAsyncClient queueAsyncClient = QueueAsyncClient.builder().endpoint(queueAsyncURL).build();
queueAsyncClient.create(metadata, timeout).subscribe(
    result -> {
      // do something when new queue created
    },
    error -> {
      // do something if something wrong happened
    },
    () -> {
      // completed, do something
    });
```

## Examples

The following sections provide several code snippets covering some of the most common Configuration Service tasks, including:
- [Build a client](#build-a-client)
- [Create a Queue](#create-a-queue)
- [Delete a queue](#delete-a-queue)
- [List the queues in account](#list-queues-in-account)
- [Get properties in Queue account](#get-properties-in-queue-account)
- [Set properties in Queue account](#set-properties-in-queue-account)
- [Get statistics of queue](#get-queue-service-statistics)
- [Enqueue message into a queue](#enqueue-message-into-a-queue)
- [Update a message in a queue](#update-a-message-in-a-queue)
- [Peek at messages in a queue](#peek-at-messages-in-a-queue)
- [Dequeue messages from a queue](#dequeue-messages-from-a-queue)
- [Delete message from a queue](#delete-message-from-a-queue)
- [Get a Queue properties](#get-a-queue-properties)
- [Set/Update a Queue metadata](#set-a-queue-metadata)

### Build a client
We have two ways of building QueueService or Queue Client. Here will take queueServiceClient as an example. Same things apply to queueClient.

First, build client from full URL/endpoint (e.g. with queueName, with SASToken and etc.)

```Java
String queueServiceURL = String.format("https://%s.queue.core.windows.net/%s", accountName, sasToken);
QueueServiceClient queueServiceClient = new QueueServiceClientBuilder().endpoint(queueServiceURL).buildClient();
```

Or

We can build the queueServiceClient from the builder using `${SASToken}` as credential.

```Java
String queueServiceURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueServiceClient queueServiceClient = new QueueServiceClientBuilder().endpoint(queueServiceURL).credential(SASToken).buildClient();
```

### Create a queue

Create a queue in the Storage Account using `${SASToken}` as credential. 
Throws StorageErrorException If the queue fails to be created.

```Java
String queueServiceURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueServiceClient queueServiceClient = new QueueServiceClientBuilder().endpoint(queueServiceURL).credential(SASToken).buildClient();

QueueClient newQueueServiceClient = queueServiceClient.createQueue("myqueue");
```
### Delete a queue

Delete a queue in the Storage Account using `${SASToken}` as credential. 
Throws StorageErrorException If the queue fails to be deleted.
```Java
String queueServiceURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueServiceClient queueServiceClient = new QueueServiceClientBuilder().endpoint(queueServiceURL).credential(SASToken).buildClient();

QueueClient newQueueServiceClient = queueServiceClient.deleteQueue("myqueue");
```

### List queues in account

List all the queues in account using `${SASToken}` as credential.
```Java
String queueServiceURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueServiceClient queueServiceClient = new QueueServiceClientBuilder().endpoint(queueServiceURL).credential(SASToken).buildClient();
// @param marker: Starting point to list the queues
// @param options: Filter for queue selection
queueServiceClient.listQueuesSegment(marker, options).forEach{
    queueItem -> {//do something}
};
```

### Get properties in queue account

Get queue properties in account, including properties for Storage Analytics and CORS (Cross-Origin Resource Sharing) rules.

Use `${SASToken}` as credential.
```Java
String queueServiceURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueServiceClient queueServiceClient = new QueueServiceClientBuilder().endpoint(queueServiceURL).credential(SASToken).buildClient();

Response<StorageServiceProperties> properties = queueServiceClient.getProperties();
```

### Set properties in queue account

Set queue properties in account, including properties for Storage Analytics and CORS (Cross-Origin Resource Sharing) rules.

Use `${SASToken}` as credential.
```Java
String queueServiceURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueServiceClient queueServiceClient = new QueueServiceClientBuilder().endpoint(queueServiceURL).credential(SASToken).buildClient();

StorageServiceProperties properties = new StorageServiceProperties() {
    // logging: some logging;
    // HourMetrics: some metrics
    // MinuteMetrics: some metrics
    // Cors: some cors
}

queueServiceClient.setProperties(properties);
```

### Get queue service statistics 
The `Get Queue Service Stats` operation retrieves statistics related to replication for the Queue service. 

Use `${SASToken}` as credential.
It is only available on the secondary location endpoint when read-access geo-redundant replication is enabled for the storage account.
```Java
String queueServiceURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueServiceClient queueServiceClient = new QueueServiceClientBuilder().endpoint(queueServiceURL).credential(SASToken).buildClient();

Response<StorageServiceStats> queueStats = queueServiceClient.getStatistics();
```

### Enqueue message into a queue
The operation adds a new message to the back of the message queue. A visibility timeout can also be specified to make the message invisible until the visibility timeout expires. 

Use `${SASToken}` as credential.
A message must be in a format that can be included in an XML request with UTF-8 encoding. The encoded message can be up to 64 KB in size for versions 2011-08-18 and newer, or 8 KB in size for previous versions.
```Java
String queueSURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).credential(SASToken).queueName("myqueue").buildClient();

queueClient.enqueueMessage("myMessage");
```

### Update a message in a queue
The operation updates a message in the message queue. Use `${SASToken}` as credential.
```Java
String queueSURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).credential(SASToken).queueName("myqueue").buildClient();
// @param messageId Id of the message
// @param popReceipt Unique identifier that must match the message for it to be updated
// @param visibilityTimeout How long the message will be invisible in the queue in seconds
queueClient.updateMessage(messageId, "new message", popReceipt, visibilityTimeout);
```

### Peek at messages in a queue
The operation retrieves one or more messages from the front of the queue. Use `${SASToken}` as credential.
```Java
String queueSURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).credential(SASToken).queueName("myqueue").buildClient();

queueClient.peekMessages().forEach(message-> {print message.messageText();});
```


### Dequeue messages from a queue
The operation retrieves one or more messages from the front of the queue. Use `${SASToken}` as credential.
```Java
String queueSURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).credential(SASToken).queueName("myqueue").buildClient();

queueClient.dequeueMessage("myMessage").forEach(message-> {print message.messageText();});
```


### Delete message from a queue
The operation retrieves one or more messages from the front of the queue. Use `${SASToken}` as credential.
```Java
String queueSURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).credential(SASToken).queueName("myqueue").buildClient();

queueClient.deleteMessage(messageId, popReceipt);
```

### Get a queue properties
The operation retrieves user-defined metadata and queue properties on the specified queue. Metadata is associated with the queue as name-values pairs.

Use `${SASToken}` as credential.
```Java
String queueSURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).credential(SASToken).queueName("myqueue").buildClient();

Response<StorageServiceProperties> properties = queueClient.getProperties();
```

### Set a queue metadata
The operation sets user-defined metadata on the specified queue. Metadata is associated with the queue as name-value pairs.

Use `${SASToken}` as credential.
```Java
String queueSURL = String.format("https://%s.queue.core.windows.net", accountName);
QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).credential(SASToken).queueName("myqueue").buildClient();

Map<String, String> metadata =  new HashMap<>() {{
    put("key1", "val1");
    put("key2", "val2");
}};
queueClient.setMetadata(metadata);
```


## Troubleshooting

## General

When you interact with queue using this Java client library, errors returned by the service correspond to the same HTTP status codes returned for [REST API][storage_rest] requests. For example, if you try to retrieve a queue that doesn't exist in your Storage Account, a `404` error is returned, indicating `Not Found`.

## Next steps

Get started with our [Queue samples][samples]:
- [QueueServiceSample][samples_queue_service]: Create, list and delete queues
- [MessageSample][samples_message]: Enqueue, peek dequeue, update, clear and delete messages. Get properties of the queue.
- [QueueExceptionSample][samples_queue_exception]: Handle the exceptions from storage queue service side.
- [AsyncSample][samples_async]: Create queue and enqueue message using async queue client call.

## Contributing
This project welcomes contributions and suggestions.  Most contributions require you to agree to a
Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us
the rights to use your contribution. For details, visit https://cla.microsoft.com.

When you submit a pull request, a CLA-bot will automatically determine whether you need to provide
a CLA and decorate the PR appropriately (e.g., label, comment). Simply follow the instructions
provided by the bot. You will only need to do this once across all repos using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/).
For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or
contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.

If you would like to become an active contributor to this project please follow the instructions provided in [Microsoft Azure Projects Contribution Guidelines](http://azure.github.io/guidelines.html).

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

<!-- LINKS -->
[source_code]: src
[api_documentation]: https://docs.microsoft.com/rest/api/storageservices/queue-service-rest-api
[storage_docs]: https://docs.microsoft.com/azure/storage/queues/storage-queues-introduction
[jdk]: https://docs.microsoft.com/java/azure/java-supported-jdk-runtime?view=azure-java-stable
[maven]: https://maven.apache.org/
[azure_subscription]: https://azure.microsoft.com/free/
[storage_account]: https://docs.microsoft.com/azure/storage/common/storage-quickstart-create-account?tabs=azure-portal
[azure_cli]: https://docs.microsoft.com/cli/azure
[sas_token]: https://docs.microsoft.com/azure/storage/common/storage-dotnet-shared-access-signature-part-1
[storage_rest]: https://docs.microsoft.com/rest/api/storageservices/queue-service-error-codes
[samples]: src/samples
[samples_queue_service]: src/samples/java/com/azure/storage/queue/QueueServiceSamples.java
[samples_message]: src/samples/java/com/azure/storage/queue/MessageSamples.java
[samples_queue_exception]: src/samples/java/com/azure/storage/queue/QueueExceptionSamples.java
[samples_async]: src/samples/java/com/azure/storage/queue/AsyncSamples.java