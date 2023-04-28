<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230424180133 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE reset_password_request (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, selector VARCHAR(20) NOT NULL, hashed_token VARCHAR(100) NOT NULL, requested_at DATETIME NOT NULL COMMENT \'(DC2Type:datetime_immutable)\', expires_at DATETIME NOT NULL COMMENT \'(DC2Type:datetime_immutable)\', INDEX IDX_7CE748AA76ED395 (user_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE reset_password_request ADD CONSTRAINT FK_7CE748AA76ED395 FOREIGN KEY (user_id) REFERENCES user (id_user)');
        $this->addSql('ALTER TABLE rating DROP FOREIGN KEY fk_ratingformation');
        $this->addSql('ALTER TABLE rating DROP FOREIGN KEY fk_ratinguser');
        $this->addSql('DROP TABLE rating');
        $this->addSql('ALTER TABLE reclamation CHANGE id_user id_user INT DEFAULT NULL');
        $this->addSql('ALTER TABLE role CHANGE id_role id_role INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE stage CHANGE id_user id_user INT DEFAULT NULL');
        $this->addSql('ALTER TABLE suivi_reclamation CHANGE id_reclamation id_reclamation INT DEFAULT NULL');
        $this->addSql('ALTER TABLE user DROP FOREIGN KEY id_role');
        $this->addSql('ALTER TABLE user CHANGE id_role id_role INT DEFAULT NULL');
        $this->addSql('ALTER TABLE user ADD CONSTRAINT FK_8D93D649DC499668 FOREIGN KEY (id_role) REFERENCES role (id_role)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE rating (id INT AUTO_INCREMENT NOT NULL, idformation_id INT NOT NULL, iduser_id INT NOT NULL, note INT NOT NULL, INDEX IDX_D889262214AF5727 (idformation_id), INDEX IDX_D8892622786A81FB (iduser_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE rating ADD CONSTRAINT fk_ratingformation FOREIGN KEY (idformation_id) REFERENCES formation (id_formation)');
        $this->addSql('ALTER TABLE rating ADD CONSTRAINT fk_ratinguser FOREIGN KEY (iduser_id) REFERENCES user (id_user)');
        $this->addSql('ALTER TABLE reset_password_request DROP FOREIGN KEY FK_7CE748AA76ED395');
        $this->addSql('DROP TABLE reset_password_request');
        $this->addSql('ALTER TABLE reclamation CHANGE id_user id_user INT NOT NULL');
        $this->addSql('ALTER TABLE role CHANGE id_role id_role INT NOT NULL');
        $this->addSql('ALTER TABLE stage CHANGE id_user id_user INT NOT NULL');
        $this->addSql('ALTER TABLE suivi_reclamation CHANGE id_reclamation id_reclamation INT NOT NULL');
        $this->addSql('ALTER TABLE user DROP FOREIGN KEY FK_8D93D649DC499668');
        $this->addSql('ALTER TABLE user CHANGE id_role id_role INT NOT NULL');
        $this->addSql('ALTER TABLE user ADD CONSTRAINT id_role FOREIGN KEY (id_role) REFERENCES role (id_role) ON UPDATE CASCADE ON DELETE CASCADE');
    }
}
