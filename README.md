# Microsserviço de Captura de Pedidos

Este microsserviço é responsável por capturar pedidos de clientes e enviá-los para uma fila FIFO do Amazon SQS, utilizando Spring Boot como framework para o desenvolvimento em Java.

[Baixar arquitetura do Projeto](https://drive.google.com/file/d/1x9fSmHhTxlP0gniZMxffmVUWQG_h49ug/view?usp=sharing)
## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 2.7.x**
- **AWS SDK para Java (SQS)**
- **Maven** para gerenciamento de dependências
- **Amazon SQS FIFO** para enfileiramento de mensagens

## Funcionalidades

- Captura de pedidos via API REST.
- Envio de pedidos para uma fila FIFO do SQS na AWS.
- A fila FIFO garante a ordem de processamento dos pedidos, com a entrega exatamente uma vez (Exactly Once).

## Pré-Requisitos

Antes de executar o microsserviço, certifique-se de que você tenha o seguinte configurado:

1. **AWS Account**: Você deve ter uma conta na AWS com permissões para criar e acessar filas SQS.
2. **Java 17+**: A versão do Java deve ser 17 ou superior.
3. **Maven**: O Maven deve estar instalado para o gerenciamento de dependências.
4. **Credenciais da AWS**: Configure suas credenciais da AWS no seu ambiente local, utilizando o AWS CLI ou variáveis de ambiente.

## Configuração da Fila SQS FIFO

Antes de executar o microsserviço, crie uma fila FIFO no SQS da AWS. A fila deve ter o nome no formato `nome-da-fila.fifo` (o sufixo `.fifo` é necessário para a fila ser FIFO).

### Configuração do arquivo `application.properties`

Configure as credenciais e a URL da fila SQS no arquivo `src/main/resources/application.properties`:

```properties
# AWS Credentials
aws.access.key.id=YOUR_AWS_ACCESS_KEY
aws.secret.access.key=YOUR_AWS_SECRET_KEY
aws.region=us-east-1

# Fila FIFO SQS
sqs.queue.url=https://sqs.us-east-1.amazonaws.com/your-account-id/your-queue-name.fifo
sqs.queue.name=your-queue-name.fifo
sqs.message.group.id=default