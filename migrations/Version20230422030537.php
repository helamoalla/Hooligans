<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230422030537 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE reponse (id INT AUTO_INCREMENT NOT NULL, message VARCHAR(200) NOT NULL, idUser INT DEFAULT NULL, idCommentaire INT DEFAULT NULL, INDEX IDX_5FB6DEC7FE6E88D7 (idUser), INDEX IDX_5FB6DEC727AD97C4 (idCommentaire), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE reponse ADD CONSTRAINT FK_5FB6DEC7FE6E88D7 FOREIGN KEY (idUser) REFERENCES user (id)');
        $this->addSql('ALTER TABLE reponse ADD CONSTRAINT FK_5FB6DEC727AD97C4 FOREIGN KEY (idCommentaire) REFERENCES commentaire (id_comment)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE reponse DROP FOREIGN KEY FK_5FB6DEC7FE6E88D7');
        $this->addSql('ALTER TABLE reponse DROP FOREIGN KEY FK_5FB6DEC727AD97C4');
        $this->addSql('DROP TABLE reponse');
    }
}
