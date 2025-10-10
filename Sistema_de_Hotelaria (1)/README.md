Projeto gerado: alberth_modificado
- Contém entidades Quarto (1:1 DetalheQuarto) e Reserva (N:1 Quarto).
- Enums: TipoQuarto, StatusQuarto, StatusReserva.
- Validações com jakarta.validation (javax.validation equivalente).
- ReservaService.hasConflict checa conflito de datas e ReservaService.save lança IllegalStateException em caso de conflito.
Como usar:
- Abrir o projeto no Eclipse/IDEA como projeto Maven.
- Rodar como Spring Boot application (classe com main em sistema.hotelaria.HotelariaApplication).
- H2 em memória está disponível para testes.
- Para testar reserva via HTTP POST:
  POST /api/reservas
  corpo JSON: exemplo:
  {
    "dataEntrada": "2025-10-20",
    "dataSaida": "2025-10-22",
    "nomeHospede": "Fulano",
    "status": "RESERVADO",
    "numeroPessoas": 2,
    "quarto": { "id": 1 }
  }
