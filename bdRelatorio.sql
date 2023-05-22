CREATE DATABASE exRelatorios
GO
USE exRelatorios
GO
CREATE TABLE medico(
id                     INT              NOT NULL,
nome                   VARCHAR(100)     NOT NULL
PRIMARY KEY(id)
)
GO
CREATE TABLE especialidade(
id                     INT              NOT NULL,
especialidade          VARCHAR(50)      NOT NULL
PRIMARY KEY(id)
)
GO
CREATE TABLE paciente(
id                     INT              NOT NULL,
nome                   VARCHAR(100)     NOT NULL,
dataNasc               DATE             NOT NULL
PRIMARY KEY(id)
)
GO
CREATE TABLE medicoEspecialidade(
idMedico               INT              NOT NULL,
idEspecialidade        INT              NOT NULL
PRIMARY KEY(idMedico, idEspecialidade)
FOREIGN KEY(idMedico) REFERENCES medico(id),
FOREIGN KEY(idEspecialidade) REFERENCES especialidade(id)
)
GO
CREATE TABLE medicoPaciente(
idMedico               INT              NOT NULL,
idPaciente             INT              NOT NULL,
dataHora               DATETIME         NOT NULL,
CID                    INT              NOT NULL,
tratamento             VARCHAR(50)      NOT NULL
PRIMARY KEY(idMedico, idPaciente, dataHora)
FOREIGN KEY(idMedico) REFERENCES medico(id),
FOREIGN KEY(idPaciente) REFERENCES paciente(id)
)
GO
INSERT INTO medico (id, nome)
VALUES             (1, 'Dr. Hans Chucrutes'),
				   (2, 'Dr. Drauzio Varella'),
				   (3, 'Dra. Beltrana Filha')
GO
INSERT INTO especialidade (id, especialidade)
VALUES                    (1, 'Psiquiatria'),
                          (2, 'Cancerologista'),
						  (3, 'Cirurgia Geral')
GO
INSERT INTO medicoEspecialidade(idMedico, idEspecialidade)
VALUES                           (1, 1),
                                 (2, 2),
								 (3, 3)

SELECT m.nome,
       e.especialidade
FROM medico m, especialidade e, medicoEspecialidade me
WHERE m.Id = me.idMedico
  AND e.Id = me.idEspecialidade

GO
INSERT INTO paciente (id, nome, dataNasc)
VALUES               (1, 'Francielle', '15/07/1998'),
                     (2, 'Fulano', '22/05/1999'),
                     (3, 'Sicrano', '22/05/2000')

SELECT * FROM paciente
GO
DROP PROCEDURE sp_insere_cid_tratamento
GO
CREATE PROCEDURE sp_insere_cid_tratamento(@nomePaciente VARCHAR(50), @nomeMedico VARCHAR(50), @CID INT, @tratamento VARCHAR(50))
AS
BEGIN
     DECLARE @idPaciente INT,
	         @idMedico INT,
			 @dataHora DATETIME

	    SET @idPaciente = (SELECT id FROM paciente WHERE nome = @nomePaciente)
		SET @idMedico = (SELECT id FROM medico WHERE nome = @nomeMedico)
		SET @dataHora = GETDATE()

		PRINT(@idMedico)
		PRINT(@idPaciente)
		PRINT(@dataHora)
		PRINT(@CID)
		PRINT(@tratamento)

		INSERT INTO medicoPaciente (idMedico, idPaciente, dataHora, CID, tratamento)
		VALUES                      (@idMedico, @idPaciente, @dataHora, @CID, @tratamento)
END

EXEC sp_insere_cid_tratamento 'Francielle', 'Dr. Hans Chucrutes', 1010, 'Férias'
EXEC sp_insere_cid_tratamento 'Fulano', 'Dr. Drauzio Varella', 1011, 'Medicação'
EXEC sp_insere_cid_tratamento 'Sicrano', 'Dra. Beltrana Filha', 1012, 'Cirurgia'

SELECT p.nome AS nomePaciente,
       p.dataNasc,
	   m.nome AS nome_medico,
	   e.especialidade,
	   CONVERT(CHAR(10),CONVERT(DATE, mp.dataHora, 103), 103) AS data,
	   CAST(CONVERT(TIME, mp.dataHora, 103) AS VARCHAR(5)) AS hora,
	   mp.CID,
	   mp.tratamento
FROM paciente p, medico m, especialidade e, medicoEspecialidade me, medicoPaciente mp
WHERE p.id = mp.idPaciente
  AND m.Id = mp.idMedico
  AND m.Id = me.idMedico
  AND e.Id = me.idEspecialidade
  AND mp.tratamento = 'Férias'